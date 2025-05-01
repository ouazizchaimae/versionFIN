import React from 'react';
import {KeyboardAvoidingView, Platform, SafeAreaView} from 'react-native';
import {globalStyle} from '../../../../../../shared/globalStyle';

import CharteChimiqueAdminTabNavigation from '../../../../../../navigation/admin/expedition/CharteChimiqueAdminTabNavigation';

const CharteChimiqueAdmin = () => {
  return (
    <SafeAreaView style={globalStyle.myContainer}>

      <KeyboardAvoidingView
        behavior={Platform.OS === 'ios' ? 'padding' : 'height'}
        style={{ flex: 1 }} >
        <CharteChimiqueAdminTabNavigation />
      </KeyboardAvoidingView>
    </SafeAreaView>
  );
};

export default CharteChimiqueAdmin;
