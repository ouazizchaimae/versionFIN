import React from 'react';
import {KeyboardAvoidingView, Platform, SafeAreaView} from 'react-native';
import {globalStyle} from '../../../../../../shared/globalStyle';

import ExpeditionAdminTabNavigation from '../../../../../../navigation/admin/expedition/ExpeditionAdminTabNavigation';

const ExpeditionAdmin = () => {
  return (
    <SafeAreaView style={globalStyle.myContainer}>

      <KeyboardAvoidingView
        behavior={Platform.OS === 'ios' ? 'padding' : 'height'}
        style={{ flex: 1 }} >
        <ExpeditionAdminTabNavigation />
      </KeyboardAvoidingView>
    </SafeAreaView>
  );
};

export default ExpeditionAdmin;
